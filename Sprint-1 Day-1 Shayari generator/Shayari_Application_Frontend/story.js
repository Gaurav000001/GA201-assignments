  
function check(){
    const dropdown1Value = document.getElementById('dropdown1').value;

    if(dropdown1Value == "joke") window.location.href = "joke.html";
    else if(dropdown1Value == "shayari") window.location.href = "index.html";
    else if(dropdown1Value == "quote") window.location.href = "quote.html";
}

function generate(){
    const length = document.getElementById('dropdown2').value;
    const output = document.getElementById("output");

    const arr = ['fantasy', 'science-fiction', 'mystery', 'romance', 'adventure', 'historical-fiction', 'thriller', 'horror', 'drama', 'comedy', 'action', 'dystopian', 'young-adult', 'historical-romance', 'superhero'];

    const checkedGenres = arr.reduce((acc, genre) => {
        const checkbox = document.getElementById(genre);
        if (checkbox.checked) {
          acc.push(genre);
        }
        return acc;
      }, []);
  
      console.log(checkedGenres);

    output.textContent = "loading ...";
    output.style.color = "green";

    fetch(`http://localhost:9090/generate.in/stories?length=length&genres=${checkedGenres.join(",")}`)
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