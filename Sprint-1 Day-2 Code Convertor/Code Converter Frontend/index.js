let e = ChangeLanguage("javascript");



function check(){
    const l = document.getElementById('dropdown1').value;
    const codeEditor = document.getElementById('code-editor');

    if (l === "javascript") e.setOption("mode", "javascript");
    else if (l === "click") e.setOption("mode", "text/x-java");
    else if (l === "python") e.setOption("mode", "python");
    else if (l === "text/x-c++src") e.setOption("mode", "text/x-c++src");

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