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

// -------- 创建视频 -----------------------------------
    let meetingInfo = xhr.response[0];
    let appID = String(meetingInfo.appID);
    let channel = String(meetingInfo.meetingID);
    let token = String(meetingInfo.token);
    let uid = Number(meetingInfo.uid);
    console.log('appID' + appID);
    console.log('token' + token);
    console.log('channel' + channel);
    console.log('uid' + uid);

    function handler(appID, channel, token, uid){
        let handleError = function(err){
            console.log("Error: ", err);
        };

        // 定义远端视频画面的容器
        let remoteContainer = document.getElementsByClassName("otherVideo1")[0];
        if(remoteContainer.hasChildNodes() && remoteContainer.childNodes.length === 5){
            remoteContainer = document.getElementsByClassName("otherVideo2")[0];
        }

        // ----将视频流添加到远端视频画面容器的函数----
        function addVideoStream(elementId){
            // 给每个流创建一个 div
            let streamDiv = document.createElement("div");
            streamDiv.classList.add('u40');
            // 将 elementId 分配到 div
            streamDiv.id = elementId;
            // 处理镜像问题
            streamDiv.style.transform = "rotateY(180deg)";
            // 将 div 添加到容器
            remoteContainer.appendChild(streamDiv);
        }

        // ----将视频流从远端视频画面容器移除的函数----
        function removeVideoStream(elementId) {
            let remoteDiv = document.getElementById(elementId);
            if (remoteDiv) remoteDiv.parentNode.removeChild(remoteDiv);
        }

        // ------------------------------- 创建客户端 -------------------------------------
        let client = AgoraRTC.createClient({
            mode: "rtc",
            codec: "vp8",
        });

        client.init(appID)

        // 加入频道
        client.join(token, channel, uid, (uid)=>{
            // 创建本地媒体流
            let localStream = AgoraRTC.createStream({
                audio: true,
                video: true,
            });
            // 初始化本地流
            localStream.init(()=>{
                // 播放本地流
                localStream.play("u30_div");
                // 发布本地流
                client.publish(localStream, handleError);
            }, handleError);
        }, handleError);

        // 有远端用户发布流时进行订阅
        client.on("stream-added", function(evt){
            client.subscribe(evt.stream, handleError);
        });
        // 订阅成功后播放远端用户的流
        client.on("stream-subscribed", function(evt){
            let stream = evt.stream;
            let streamId = String(stream.getId());
            addVideoStream(streamId);
            stream.play(streamId);
        });

        // 远端用户取消发布流时，关闭及移除对应的流。
        client.on("stream-removed", function(evt){
            let stream = evt.stream;
            let streamId = String(stream.getId());
            stream.close();
            removeVideoStream(streamId);
        });
        // 远端用户离开频道时，关闭及移除对应的流。
        client.on("peer-leave", function(evt){
            let stream = evt.stream;
            let streamId = String(stream.getId());
            stream.close();
            removeVideoStream(streamId);
        });
    }
    handler(appID, channel, token, uid);

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

};
