let xhr = new XMLHttpRequest();
xhr.open('get', 'getCompany');
xhr.responseType = 'json';
xhr.send();

xhr.onerror = function (){
    console.log("企业信息请求失败！");
}
xhr.onload = function (){
    if(xhr.status !== 200){
        alert('Error' + xhr.status);
        return;
    }

    let companies = xhr.response;
    let container = document.querySelector(".companiesContainer");
    for(let i=0; companies[i] != null; i++){
        let node = document.createElement("label")
        node.innerHTML = "企业名：" + companies[i] + "<input type='checkbox'>";
        container.append(node);
    }
    if(companies[0] == null){
        let node = document.createElement("span");
        node.innerHTML = "尚未有企业报名项目！";
        container.append(node);
    }
}