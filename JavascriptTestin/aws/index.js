
import AWS from 'aws-sdk'
const api = new AWS.ApiGatewayManagementApi({
  endpoint: '63mgnuyfr6.execute-api.eu-north-1.amazonaws.com/production/'
})

const waitingPlayers = [];
const playerEnemy = new Map();
const playerGameId = new Map();

const handler = async (event) => {
  console.log(event)
  
  const route  = event.requestContext.routeKey
  const connectionId = event.requestContext.connectionId
  
  switch(route) {
    case '$connect':
      console.log('connection occured')
      break
    case '$disconnect':
      console.log('disconnection occured')
      break
    case 'message':
      let msg = JSON.parse(event.body).msg
      console.log("conn id: " + connectionId + " and msg is: " + msg )
      switch(msg) {
        case 'findEnemy':
          if(waitingPlayers.length == 0) {
            waitingPlayers.push(connectionId);
          }
          else {
            let firstPlayer = waitingPlayers[0]
            let secondPlayer = connectionId
            playerEnemy.set(firstPlayer, secondPlayer)
            playerEnemy.set(secondPlayer, firstPlayer)
            waitingPlayers.splice(0,1)
            let gameId = firstPlayer + secondPlayer
            playerGameId.set(firstPlayer, gameId)
            playerGameId.set(secondPlayer, gameId)
            console.log("first player: " + firstPlayer)
            console.log("second player: " + secondPlayer)
            await reply("gameid:" + gameId, secondPlayer)
          }
          break
        case 'enemyFound':
          await reply("to_duel_page:" + playerGameId.get(connectionId), playerEnemy.get(connectionId))
          break
        case 'getIntoDuelPage':
          await reply("Go into duel page", playerEnemy.get(connectionId))
          await reply("Go into duel page", connectionId)
          break
        case 'sendToEnemy':
          await reply("Get data from server:" + connectionId,  playerEnemy.get(connectionId))
          break
        case 'sendTrigger':
          await reply("game",  playerEnemy.get(connectionId))
          break
        case 'mulliganEnded':
          await reply("mulligan",  playerEnemy.get(connectionId))
          break
        default:
      }
      break
    default:
      console.log('Unknown route hit ' , route)
  }
  
  return {
    statusCode:200 
  }
}


async function reply(response, connectionId) {
  const data = { message: response}
  const params = {
    ConnectionId: connectionId,
    Data: Buffer.from(JSON.stringify(data))
  }
  
  return api.postToConnection(params).promise()
}

export {handler}
