// -------------------------- 当前网速检测 --------------------------------

let times1 = 0;//用来计数图片下载次数，防止缓存过多
let interval1 = setInterval("getSpeed()", 1000);

function getSpeed(){
    let startTime = new Date().getTime();
    let img = new Image();
    img.src = "./images/logo2.a2ebc408.png?timesStamp=" + startTime;
    let endTime;
    let size = 21263; // 图片大小
    let speed;
    img.onload = function(){

        endTime = new Date().getTime();
        speed = parseInt(size / (endTime - startTime));
        let unit = "KB/S";
        if(speed >= 1024){
            speed = (speed / 1024).toFixed(2);
            unit = "MB/S";
        }
        document.getElementById("network-downspeed").innerHTML = "当前带宽："+ speed + unit + "<br/>请求用时：" + (endTime - startTime) / 1000 + "s";
    };
    times1++;
    if(times1 == 300){
        clearInterval(interval1);
    }
}

// ------------------------ 浏览器版本检测 -------------------------
//获取浏览器信息
let browser = getBrowserInfo();
//根据正则将所有数字、‘.’‘/’全部去掉，剩下浏览器名
let b_name = (browser + "").replace(/[0-9./]/ig, "");
//根据正则将所有非数字全部去掉，剩下版本
let b_version = parseInt((browser + "").replace(/[^0-9.]/ig, ""));
console.log("正在使用" + b_name + "浏览器，" + "版本是" + b_version);
document.getElementById('b-information').innerHTML = b_name + "浏览器，" + "版本是" + b_version;

/**
 * 获取浏览器版本
 * @returns {string|RegExpMatchArray}
 */
function getBrowserInfo() {
    let agent = navigator.userAgent.toLowerCase();
    let regStr_ie = /msie [\d.]+;/gi;
    let regStr_ff = /firefox\/[\d.]+/gi
    let regStr_chrome = /chrome\/[\d.]+/gi;
    let regStr_saf = /safari\/[\d.]+/gi;

    //判断是否IE<11浏览器
    let isIE = agent.indexOf("compatible") > -1 && agent.indexOf("msie" > -1);

    //判断是否IE的Edge浏览器
    let isEdge = agent.indexOf("edge") > -1 && !isIE;

    //判断是否是IE11
    let isIE11 = agent.indexOf('trident') > -1 && agent.indexOf("rv:11.0") > -1;

    //IE
    if (isIE) {
        let reIE = new RegExp("msie (\\d+\\.\\d+);");
        reIE.test(agent);
        let fIEVersion = parseFloat(RegExp["$1"]);
        if (fIEVersion == 7) {
            return "IE/7";
        } else if (fIEVersion == 8) {
            return "IE/8";
        } else if (fIEVersion == 9) {
            return "IE/9";
        } else if (fIEVersion == 10) {
            return "IE/10";
        }
    } //isIE end

    //IE11
    if (isIE11) {
        return "IE/11";
    }

    //firefox
    if (agent.indexOf("firefox") > 0) {
        return agent.match(regStr_ff);
    }

    //Safari
    if (agent.indexOf("safari") > 0 && agent.indexOf("chrome") < 0) {
        return agent.match(regStr_saf);
    }

    //Chrome
    if (agent.indexOf("chrome") > 0) {
        return agent.match(regStr_chrome);
    }
}

// ------------------------ 摄像头检测 ----------------------------
let mediaStream;
let recorderFile;
let stopRecordCallback;
let openBtn = document.getElementById("openCamera");
let startBtn = document.getElementById("start-recording");
let saveBtn = document.getElementById("save-recording");
openBtn.onclick = function () {
    this.disabled = true;   // 打开摄像头按钮禁用
    startBtn.disabled = false;      //开始录制按钮解除禁用
    openCamera();   // 打开摄像头
};

startBtn.onclick = function () {
    this.disabled = true;
    startRecord();
};

saveBtn.onclick = function () {
    saver();
    // alert('Drop WebM file on Chrome or Firefox. Both can play entire file. VLC player or other players may not work.');
};

let mediaRecorder;
let videosContainer = document.getElementById('videos-container');

function openCamera() {
    let len = videosContainer.childNodes.length;
    for (let i = 0; i < len; i++) { // 删除视频框的子节点
        videosContainer.removeChild(videosContainer.childNodes[i]);
    }

    // 创建视频标签，并设置属性
    let video = document.createElement('video');
    let videoWidth = 320;
    let videoHeight = 240;
    video.controls = false;
    video.muted = true;
    video.width = videoWidth;
    video.height = videoHeight;

    //
    MediaUtils.getUserMedia(true, false, function (err, stream) {
        if (err) {
            throw err;
        } else {
            // 通过 MediaRecorder 记录获取到的媒体流
            console.log();
            mediaRecorder = new MediaRecorder(stream);
            mediaStream = stream;
            let chunks = [], startTime = 0;
            video.srcObject = stream;
            video.play();

            videosContainer.appendChild(video);
            mediaRecorder.ondataavailable = function (e) {
                mediaRecorder.blobs.push(e.data);
                chunks.push(e.data);
            };
            mediaRecorder.blobs = [];

            mediaRecorder.onstop = function (e) {
                recorderFile = new Blob(chunks, { 'type': mediaRecorder.mimeType });
                chunks = [];
                if (null != stopRecordCallback) {
                    stopRecordCallback();
                }
            };
        }
    });
}

// 停止录制
function stopRecord(callback) {
    stopRecordCallback = callback;
    // 终止录制器
    mediaRecorder.stop();
    // 关闭媒体流
    MediaUtils.closeStream(mediaStream);
}

let MediaUtils = {
    /**
     * 获取用户媒体设备(处理兼容的问题)
     * @param videoEnable {boolean} - 是否启用摄像头
     * @param audioEnable {boolean} - 是否启用麦克风
     * @param callback {Function} - 处理回调
     */
    getUserMedia: function (videoEnable, audioEnable, callback) {
        // 多浏览器匹配
        navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia
            || navigator.msGetUserMedia || window.getUserMedia;
        // 请求的媒体类型和相对应的参数,这里是摄像头和麦克风
        let constraints = { video: videoEnable, audio: audioEnable };
        if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
            navigator.mediaDevices.getUserMedia(constraints).then(function (stream) {
                callback(false, stream);
            })['catch'](function (err) {
                callback(err);
            });
        } else if (navigator.getUserMedia) {
            navigator.getUserMedia(constraints, function (stream) {
                callback(false, stream);
            }, function (err) {
                callback(err);
            });
        } else {
            callback(new Error('Not support userMedia'));
        }
    },

    /**
     * 关闭媒体流
     * @param stream {MediaStream} - 需要关闭的流
     */
    closeStream: function (stream) {
        if (typeof stream.stop === 'function') {
            stream.stop();
        }
        else {
            let trackList = [stream.getAudioTracks(), stream.getVideoTracks()];

            for (let i = 0; i < trackList.length; i++) {
                let tracks = trackList[i];
                if (tracks && tracks.length > 0) {
                    for (let j = 0; j < tracks.length; j++) {
                        let track = tracks[j];
                        if (typeof track.stop === 'function') {
                            track.stop();
                        }
                    }
                }
            }
        }
    }
};

function startRecord() {
    mediaRecorder.start();
    setTimeout(function () {
        // 结束
        stopRecord(function () {
            alert("录制成功!");
            openBtn.disabled = false;
            saveBtn.disabled = false;
            //send();
        });
    }, 5000);
}

function saver() {
    let file = new File([recorderFile], 'msr-' + (new Date).toISOString().replace(/:|\./g, '-') + '.mp4', {
        type: 'video/mp4'
    });
    saveAs(file);
}

function send() {
    let file = new File([recorderFile], 'msr-' + (new Date).toISOString().replace(/:|\./g, '-') + '.mp4', {
        type: 'video/mp4'
    });
    let data = new FormData();
    data.append("username", "test");
    data.append("userfile", file);

    let req = new XMLHttpRequest();
    req.open("POST", "com.spinsoft.bip.frame.utils.image.saveMp4.biz.ext");
    req.send(data);
}


//--------------网速实时刷新--------------------
/*
function refreshData() {

    $.post("url",{},function(data){
        if (data.res == 1) {
            $("#network-speed").text(data);
        }
    });
}
*/