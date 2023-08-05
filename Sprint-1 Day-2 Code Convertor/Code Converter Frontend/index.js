let e = ChangeLanguage("javascript");



function check(){
    const l = document.getElementById('dropdown1').value;
    const codeEditor = document.getElementById('code-editor');

    if (l === "javascript") e.setOption("mode", "javascript");
    else if (l === "click") e.setOption("mode", "text/x-java");
    else if (l === "python") e.setOption("mode", "python");
    else if (l === "text/x-c++src") e.setOption("mode", "text/x-c++src");
    // console.log(e.getValue());

    // Refresh the CodeMirror instance to update the mode
    e.refresh();
}


function ChangeLanguage(language){
    // let languageName = [["javascript", "javascript"], ["java", "click"], ["python", "python"], ["c++", "text/x-c++src"]];

    // Initialize CodeMirror with additional options
    return CodeMirror.fromTextArea(document.getElementById('code-editor'), {
        lineNumbers: true,
        mode: language,
        theme: 'default',
        tabSize: 1,
        autofocus: true,
        lineWrapping: true, // Enable line wrapping
        readOnly: false, // Set to true to make the editor read-only
        autoCloseBrackets: true, // Automatically close brackets
    });
}

async function run(){
    let code = e.getValue();
    let language = document.getElementById('dropdown2').value;
    let output = document.getElementById('response-container');
    console.log(code);


    let response = await fetch(`http://localhost:9090/convert.in/run`, {
        method: "POST",
        headers: {
        'Content-Type': 'text/plain',
        },
        body: code
    })
    .then(response => response.json())
    .then(result => {

        output.innerText = result;

    })
    .catch(error => {
        output.textContent = "Server down please try again later...";
        output.style.color = "red";
    });
}