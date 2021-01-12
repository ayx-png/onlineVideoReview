//pc.js
//设计图纸为1366*768
function pagePc(){
    let rootValue = 100;
    let pc = rootValue / 1920; // 表示1366的设计图,使用100px的默认值
    let width = window.innerWidth; // 当前窗口的宽度
    let rem;
    if(width < 500){
        width = 720;
    }
    // let currentHeight = (width * 798) / 1366;
    // if (height < currentHeight) {
    //     // 当前屏幕高度小于应有的屏幕高度，就需要根据当前屏幕高度重新计算屏幕宽度
    //     width = (height * 1366) / 798;
    // }
    rem = width * pc; // 以默认比例值乘以当前窗口宽度,得到该宽度下的相应font-size值
    document.documentElement.style.fontSize = rem + "px";
}

window.onloaded = pagePc();
window.onresized = pagePc();