let xhr = new XMLHttpRequest();

xhr.open('get','getUserProject');
xhr.responseType = 'json';
xhr.send();

xhr.onerror = function (){
    console.log("会议信息请求失败")
}
xhr.onload = function (){
    if(xhr.status !== 200){
        alert('Error' + xhr.status);
        return;
    }
    let project = xhr.response;
    let projectName = project.projectName;

    addProject(projectName);
    function addProject(projectName){
        let projectContainer = document.querySelector(".projectForm");
        let projectNode = document.createElement('li');
        let cookies = document.cookie.split(';');
        let currentUser = '';
        for(let cookie of cookies){
            cookie = cookie.trim();
            if(cookie.indexOf("user") == 0)
                currentUser = cookie.substr("user=".length ,cookie.length);
        }
        console.log(currentUser);
        if(currentUser == project.projectHost){
            projectNode.innerHTML = "<a href='pre-test.html'>" + projectName + "</a>" + "    (你是项目主持人，请点击项目名创建会议)"
                + "<a href='approvalCompany.jsp'><button type='button'>审批企业</button></a>";
        }
        else{
            projectNode.innerHTML = "<a href='joinMeeting.jsp'>" + projectName + "</a>" + "    (点击项目名可加入会议)";
        }
        projectContainer.append(projectNode);
    }
}

