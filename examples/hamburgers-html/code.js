const form = document.querySelector("form");
const burgerName = document.getElementById("burgerName");
const calories = document.getElementById("calories");
const burgerContainer = document.getElementById("burgerContainer");
const burgerTableContainer = document.getElementById("burgerTableContainer");

let burgers = [];

form.addEventListener("submit", formSubmit);

function formSubmit(evt) {
    evt.preventDefault();

    const burger = {
        "name": burgerName.value,
        "calories": calories.value
    };

    burgers.push(burger);
    displayBurgerMenu();
    displayBurgerTable();
}

function removeBurger(name) {
    burgers = burgers.filter(b => b.name !== name);
    displayBurgerMenu();
    displayBurgerTable();
}

function displayBurgerMenu() {
    let result = '';
    burgers.forEach(b => {
        result += `<div class="burger-row">
        <div>${b.name}</div><div>${b.calories}</div><div>
        <button type="button" onclick="removeBurger('${b.name}')">Delete</button></div>
        </div>`
    });
    burgerContainer.innerHTML = result;
}

function displayBurgerTable() {
    let result = '<table><tr><td>Name</td><td>Calories</td><td></td></tr>';

    burgers.forEach(b => {
        result += `<tr><td>${b.name}</td><td>${b.calories}</td><td>
        <button type="button" onclick="removeBurger('${b.name}')">Delete</button></td></tr>`;
    });
    result += '</table>'
    burgerTableContainer.innerHTML = result;
}