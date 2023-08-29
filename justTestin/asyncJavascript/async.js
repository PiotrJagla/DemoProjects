import fetch from 'node-fetch'


async function func1() {
    return setTimeout(() => {
        console.log("func1");

    },100)
}
async function func2() {
    return setTimeout(() => {
        console.log("func2");

    },1000)
}
async function func3() {
    return setTimeout(() => {
        console.log("func3");
        return 1;

    },6000)
}


Promise.resolve().then(() => console.log(2))
console.log(1)