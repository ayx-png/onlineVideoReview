let xhr = new XMLHttpRequest();

xhr.open("GET", 'getUserMeeting');
xhr.responseType = 'json';
xhr.send();

xhr.onerror = function (){
    console.log("会议信息请求失败！");
}
xhr.onload = function () {
    if(xhr.status !== 200){
        alert('Error:' + xhr.status);
        return;
    }

    let meetingInfo = xhr.response[0];
    let appID = String(meetingInfo.appID);
    let channel = String(meetingInfo.meetingID);
    let token = String(meetingInfo.token);
    let uid = Number(meetingInfo.uid);
    let audio = String(meetingInfo.audio) === ""; // meetingInfo.audio===""时，即audio为true，不静音
    let video = String(meetingInfo.video) === ""; // meetingInfo.video===""时，即video为true，摄像头打开
    let userArray = xhr.response[3];
    let map = new Map();
    let users = [];
    for(let i=0; i<userArray.length; i+=2){ // 偶数id，奇数username
        map.set(userArray[i], userArray[i+1]);
        users.push(userArray[i+1]);
    }
// --------- 显示页面信息 -------------------------------
    let meetingShowMsg = xhr.response[1];
    let projectMsg = xhr.response[2];
    let host = meetingShowMsg.admin;
    let topic = meetingShowMsg.topic;
    let project = projectMsg.projectName;
    let username = meetingInfo.username;
    let hostContainer = document.querySelector(".host");
    let topicContainer = document.querySelector(".topic");
    let projectContainer = document.querySelector(".project");
    let userContainer = document.querySelector(".user");
    let meetingTitleContainer = document.querySelector(".meetingTitle");

    hostContainer.innerHTML = "会议主持人：" + host;
    topicContainer.innerHTML = "会议主题：" + topic + "/";
    projectContainer.innerHTML = "项目名称：" + project + "/";
    userContainer.innerHTML = username;
    meetingTitleContainer.innerHTML = topic;

// -------- 用户列表 ------------------------------------
    let userListContainer = document.querySelector(".userList");
    if(users.includes(host)){
        users.splice(users.indexOf(host),2);
        users.unshift(host);
    }
    if(meetingInfo.username !== host){ // 普通用户的用户列表，仅展示用户名
        for(let user of users){
            if(user === meetingInfo.username){
                let node = document.createElement("div");
                node.classList.add("u11");
                node.innerHTML = "<div><p><span>" + user + "</span></p></div>" +
                    "<select id='userSelect'>" + "<option value=''>--音视频设置--</option>" +
                    "<option value='microphone'>麦克风:开/关</option>" +
                    "<option value='camera'>摄像头:开/关</option></select>>";
                userListContainer.append(node);
            }
            else {
                let node = document.createElement("div");
                node.classList.add("u11");
                node.innerHTML = "<div><p><span>" + user + "</span></p></div>";
                userListContainer.append(node);
            }
        }
    }
    else{
        for(let user of users){
            let node = document.createElement("div");
            node.classList.add("u11");
            node.innerHTML = "<div><p><span>" + user + "</span></p></div>" +
                "<select id='" + user + "Select" +"'><option value=''>--用户管理--</option>" +
                "<option value='microphone'>麦克风:开/关</option>" +
                "<option value='camera'>摄像头:开/关</option>" +
                "<option value='userInfo'>查看用户信息</option></select>";
            userListContainer.append(node);
        }
    }

// -------- 创建视频 -----------------------------------
    // console.log('appID' + appID);
    // console.log('token' + token);
    // console.log('channel' + channel);
    // console.log('uid' + uid);
    handler(appID, channel, token, uid, audio, video);

    function handler(appID, channel, token, uid, audio, video) {

        // ------------------------------- 创建客户端 -------------------------------------
        let rtc = {
            client: null,
            localAudioTrack: null,
            localVideoTrack: null,
            // remoteStreams: [],
            // params: {}
        }
        let options = {
            appId: appID,
            channel: channel,
            token: token,
            uid: uid,
            video: video,
            audio: audio,
        }

        // 定义远端用户视频容器
        let remoteContainer = document.getElementsByClassName("otherVideo1")[0];
        let timeID= setInterval(function (){
            if(remoteContainer.hasChildNodes() && remoteContainer.childNodes.length === 5){
                remoteContainer = document.getElementsByClassName("otherVideo2")[0];
                clearInterval(timeID);
            }
        }, 1);

        startBasicCall();

        async function startBasicCall() {
            // 1. 创建本地客户端
            rtc.client = AgoraRTC.createClient({
                mode: "rtc",
                codec: "vp8",
            });

            // 2. 订阅远端频道
            rtc.client.on("user-published", async (user, mediaType) => {
                // 开始订阅远端用户。
                await rtc.client.subscribe(user, mediaType);
                console.log("subscribe success");

                // 表示本次订阅的是视频。
                if (mediaType === "video") {
                    // 订阅完成后，从 `user` 中获取远端视频轨道对象。
                    const remoteVideoTrack = user.videoTrack;
                    // 动态在远端用户容器插入一个 DIV 节点作为播放远端视频轨道。
                    let remoteContainerForOne = document.createElement("div");
                    remoteContainerForOne.classList.add("u40");
                    const playerContainer = document.createElement("div");
                    // 给这个 DIV 节点指定一个 ID，这里指定的是远端用户的 UID。
                    playerContainer.id = user.uid.toString();
                    let usernameForID = map.get(playerContainer.id);
                    let pForName = document.createElement("p");
                    pForName.innerHTML = usernameForID;
                    pForName.style.width = "171px";
                    pForName.style.height = "10px";
                    pForName.style.textAlign = "center";
                    playerContainer.style.width = "171px";
                    playerContainer.style.height = "108px";
                    remoteContainerForOne.append(playerContainer);
                    remoteContainerForOne.append(pForName);
                    remoteContainer.append(remoteContainerForOne);

                    // 订阅完成，播放远端音视频。
                    // 传入 DIV 节点，让 SDK 在这个节点下创建相应的播放器播放远端视频。
                    remoteVideoTrack.play(playerContainer);

                    // 也可以只传入该 DIV 节点的 ID。
                    // remoteVideoTrack.play(playerContainer.id);
                }

                // 表示本次订阅的是音频。
                if (mediaType === "audio") {
                    // 订阅完成后，从 `user` 中获取远端音频轨道对象。
                    const remoteAudioTrack = user.audioTrack;
                    // 播放音频因为不会有画面，不需要提供 DOM 元素的信息。
                    remoteAudioTrack.play();
                }
            });

            rtc.client.on("user-unpublished", (user, mediaType) => {
                if (mediaType === "video") {
                    // 获取刚刚动态创建的 DIV 节点。
                    const playerContainer = document.getElementById(user.uid.toString()).parentNode;
                    // 销毁这个节点。
                    playerContainer.remove();
                }
            });

            // 3. 加入目标频道
            await rtc.client.join(options.appId, options.channel, options.token, options.uid);

            // 4. 创建并发布本地音视频轨道
            // 4.1 通过麦克风采集的音频创建本地音频轨道对象。
            rtc.localAudioTrack = await AgoraRTC.createMicrophoneAudioTrack();
            // 4.2 通过摄像头采集的视频创建本地视频轨道对象。
            rtc.localVideoTrack = await AgoraRTC.createCameraVideoTrack();
            // 4.3 将这些音视频轨道对象发布到频道中。
            await rtc.client.publish([rtc.localAudioTrack, rtc.localVideoTrack]);

            console.log("publish success!");

            // 4.5 播放本地流
            let myContainer = document.querySelector(".mainVideo");
            let myVideo = document.createElement("div");
            myVideo.id = options.uid.toString();
            myVideo.style.width = "982px";
            myVideo.style.height = "483px";
            myContainer.append(myVideo);
            if(!options.video){
                await rtc.localVideoTrack.play(myVideo);
                await rtc.localVideoTrack.setEnabled(options.video);
            }else {
                await rtc.localVideoTrack.play(myVideo);
            }
            if(!options.audio){
                await rtc.localAudioTrack.setEnabled(options.audio);
            }else{
                await rtc.localAudioTrack.play();
            }


            // 5. 用户自身离开频道
            async function leaveCall() {
                // 销毁本地音视频轨道。
                rtc.localAudioTrack.close();
                rtc.localVideoTrack.close();

                // 遍历远端用户。
                rtc.client.remoteUsers.forEach(user => {
                    // 销毁动态创建的 DIV 节点。
                    const playerContainer = document.getElementById(user.uid);
                    playerContainer && playerContainer.remove();
                });

                // 离开频道。
                await rtc.client.leave();
            }

            // 摄像头、麦克风禁用与开启
            let AVSelect = document.querySelector("#userSelect");
            if(AVSelect){
                let selections = AVSelect.options;
                AVSelect.onchange = function() {
                    let index = AVSelect.selectedIndex;
                    if(selections[index].value === "camera"){
                        options.video = !options.video;
                        rtc.localVideoTrack.setEnabled(options.video);
                    }else if(selections[index].value === "microphone"){
                        options.audio = !options.audio;
                        rtc.localAudioTrack.setEnabled(options.audio);
                    }
                }
            }
            let hostSelectClass = "#" + host + "Select";
            let hostSelect = document.querySelector(hostSelectClass);
            if(hostSelect){
                let hostSelections = hostSelect.options;
                hostSelect.onchange = function(){
                    let index = hostSelect.selectedIndex;
                    if(hostSelections[index].value === "camera"){
                        options.video = !options.video;
                        rtc.localVideoTrack.setEnabled(options.video);
                    }else if(hostSelections[index].value === "microphone"){
                        options.audio = !options.audio;
                        rtc.localAudioTrack.setEnabled(options.audio);
                    }
                }
            }
        }
    }

};
