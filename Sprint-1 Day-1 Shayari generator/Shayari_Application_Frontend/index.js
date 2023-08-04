  
function check(){
    const dropdown1Value = document.getElementById('dropdown1').value;

    if(dropdown1Value == "joke") window.location.href = "joke.html";
    else if(dropdown1Value == "story") window.location.href = "story.html";
    else if(dropdown1Value == "quote") window.location.href = "quote.html";
}

function generate(){
    const length = document.getElementById('dropdown2').value;
    let keyword = document.getElementById("keyword").value;
    const output = document.getElementById("output");

    if(keyword == "") keyword = "love";

    output.textContent = "loading ...";
    output.style.color = "green";

    fetch(`http://localhost:9090/generate.in/shayaries?length=${length}&topic=${keyword}`)
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