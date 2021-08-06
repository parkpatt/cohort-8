// click
const buttons = document.querySelectorAll("button");
const clickLog = document.getElementById("clickLog");

let aClicks = 0;

buttons[0].addEventListener("click", () => {
    aClicks++;
    clickLog.value = `Button A was clicked ${aClicks} times.`;
});

// focus/blur
const focusBlurInputs = document.querySelectorAll(".focus-blur");
const focusAndBlurLog = document.getElementById("focusAndBlurLog");

focusBlurInputs[0].addEventListener("focus", () => {
    const div = document.createElement("div");
    div.textContent = `Input A received focus. It's current value is: ${focusBlurInputs[0].value}.`;
    focusAndBlurLog.append(div);
});

focusBlurInputs[0].addEventListener("blur", () => {
    const div = document.createElement("div");
    div.textContent = `Input A blurred. It's current value is: ${focusBlurInputs[0].value}.`;
    focusAndBlurLog.append(div);
});

// change

function logChange(msg) {
    const div = document.createElement("div"); 
    div.textContent = msg;
    document.getElementById("changeLog").append(div);
}

document.getElementById("chkA").addEventListener("change", function(evt) {
    logChange(`change! target.id: ${evt.target.id}`);
});

document.getElementById("selectA").addEventListener("change", function(evt) {
    logChange(`change! selected value: ${evt.target.value}`);
});