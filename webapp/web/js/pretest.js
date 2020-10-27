//浏览器版本检测
function getBrowserInfo() {

    var agent = navigator.userAgent.toLowerCase();
    
    var regStr_ie = /msie [\d.]+;/gi;
    
    var regStr_ff = /firefox\/[\d.]+/gi
    
    var regStr_chrome = /chrome\/[\d.]+/gi;
    
    var regStr_saf = /safari\/[\d.]+/gi;
    
    
    
           //判断是否IE<11浏览器  
    
    var isIE = agent.indexOf("compatible") > -1 && agent.indexOf("msie" > -1); 
    
            //判断是否IE的Edge浏览器 
    
    var isEdge = agent.indexOf("edge") > -1 && !isIE;  
    
            //判断是否是IE11
    
    var isIE11 = agent.indexOf('trident') > -1 && agent.indexOf("rv:11.0") > -1;
    
    
    
    if (isIE) {
    
        var reIE = new RegExp("msie (\\d+\\.\\d+);");
    
        reIE.test(agent);
    
        var fIEVersion = parseFloat(RegExp["$1"]);
    
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
//网速实时刷新
function refreshData() {

  $.post("url",{},function(data){

    if (data.res == 1) {

        $("#network-speed").text(data);

    }

  });  
}     
