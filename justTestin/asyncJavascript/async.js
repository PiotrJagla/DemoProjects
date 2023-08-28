import fetch from 'node-fetch'

async function boilWater() {
    setTimeout(() => {
        console.log("water is boiled")
    }, 1000)
}

async function afterBoiling() {
    console.log("after boiling");
}

async function asyncCall() {


    // await boilWater().then(console.log("Costam"))
    new Promise(console.log("costam"))
    // await afterBoiling()
    // for(let i = 0 ; i < 4 ; ++i) {
    //     await setTimeout(() => {
    //         console.log(i)
    //     }, 1000)
    // }
}

asyncCall()