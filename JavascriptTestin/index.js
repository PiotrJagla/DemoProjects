const socket = new WebSocket('wss://63mgnuyfr6.execute-api.eu-north-1.amazonaws.com/production/')
console.log('start')

const startTime = 0;
const endTime = 0;

socket.addEventListener('open', e => {
    const startTime = new Date();
    console.log(e)
    console.log("websocket is connected")
})

socket.addEventListener('close', e => {
    const endTime = new Date();
    console.log("time from open to close is: " + (endTime - startTime))
    console.log(e)
    console.log("websocket is closed")
})

socket.addEventListener('message', e => {
    console.log(JSON.parse(e.data).message)
    // console.log("your answer is:", JSON.parse(e.data).message)
})

window.ask = function (msg) {
    const payload = {
        action: 'message',
        msg
    }
    socket.send(JSON.stringify(payload))
}
