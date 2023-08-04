  
function check(){
    const dropdown1Value = document.getElementById('dropdown1').value;

    if(dropdown1Value == "shayari") window.location.href = "index.html";
    else if(dropdown1Value == "story") window.location.href = "story.html";
    else if(dropdown1Value == "quote") window.location.href = "quote.html";
}

function generate(){
    let keyword = document.getElementById("keyword").value;
    const output = document.getElementById("output");

    if(keyword == "") keyword = "knock-knock";

    output.textContent = "loading ...";
    output.style.color = "green";

    fetch(`http://localhost:9090/generate.in/jokes?type=${keyword}`)
    .then(response => response.text())
    .then(result => {
        output.textContent = result;
        output.style.color = "purple";
    })
    .catch(error => {
        output.textContent = "Server down please try again later...";
        output.style.color = "red";
    });

}