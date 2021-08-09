
// filter
const arr = [1, 2, 3, 4, 5];
const evens = arr.filter(num => num % 2 == 0);
console.log(evens);


// reduce
const sum = arr.reduce((acc, num) => { 
    return acc + num;
}, 0);


const reducerFunc = (acc, num) => acc + num;

const sum2 = arr.reduce(reducerFunc, 0);

console.log(sum);
console.log(sum2);


// object spread
const person = {
    title: "Mr",
    firstName: "",
    lastName: "Kendrick"
};

const brendan = {...person};


const addFirstName = {
    firstName: "Brendan"
};

console.log({...brendan, ...addFirstName});


// object destructuring
const { title, lastName } = person;

console.log(`${title} ${lastName}`);



// function spread params

const funcWithVariableArity = (...parms) => {

}

const exampleNArity = (a, b, ...theRest) => { 

}

const stringFormat = (str, ...rest) => {
    '%s'
} 


