  
function check(){
    const dropdown1Value = document.getElementById('dropdown1').value;

    if(dropdown1Value == "joke") window.location.href = "joke.html";
    else if(dropdown1Value == "story") window.location.href = "story.html";
    else if(dropdown1Value == "shayari") window.location.href = "index.html";
}

function generate(){
    const theme = document.getElementById('dropdown2').value;
    const output = document.getElementById("output");


    output.textContent = "loading ...";
    output.style.color = "green";

    fetch(`http://localhost:9090/generate.in/quotes?theme=${theme}`)
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