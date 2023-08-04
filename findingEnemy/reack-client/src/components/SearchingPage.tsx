import React, {useState} from 'react';
import SockJS from 'sockjs-client';
import {over} from 'stompjs';

interface message {
  receiver:string;
  sender:string;
  message:string;
}


var serverURL: string = 'http://localhost:8082';
var stompClient: any = null;
const SearchingPage = () => {
    const [userName, setUserName] = useState<string>("");
    const [isConnected, setIsConnected] = useState<boolean>(false);
    const [isEnemyFound, setIsEnemyFound] = useState<boolean>(false);
    const [points, setPoints] = useState<number>(0);
  const connectToServer= () => {
    let Sock=new SockJS(serverURL + '/ws');
    stompClient = over(Sock);
    stompClient.connect({}, onConnected);

  }
  const onConnected = () => {
    setIsConnected(true);
    stompClient.subscribe('/user/' + userName + '/private', onMessageReceived);

  }

  const onMessageReceived = (payload:any) => {
    console.log('Your name is: ' + payload.body);

    if(payload.body === "Found enemy") {

      setIsEnemyFound(true);
      fetch(serverURL + '/button', {
        method: 'PUT',
        headers: {'Content-Type': 'text/plain',},
        body: userName,
      });
    }
  }


  const startSearching = () => {
    stompClient.send('/app/findEnemy', {}, userName);
  }
  const connect = () => {
    connectToServer();
  }

  const increase = () => {

      fetch(serverURL + '/button', {
        method: 'POST',
        headers: {'Content-Type': 'text/plain',},
        body: userName,
      });
      fetch(serverURL + `/button/getPoints/${userName}`)
      .then((res) => res.json())
      .then((data:number ) => {
        setPoints(data);
      }).catch(console.error);

  }

  return (
    <div className="App">
      {!isConnected? <div>connect to search</div>: <button onClick={startSearching}>search for opponent</button>}
      <div>
        {isConnected? <p>{userName}</p> :
        <div>
         <input id='user-name' placeholder='enter user name' value={userName} name='username' onChange={(event:any) => {setUserName(event.target.value)}}/>
         <button onClick={connect}>connect to server</button>
        </div>
        }
      </div>
      {isEnemyFound?
      <div>
        <button onClick={increase}>Click</button>
        <p>{points}</p>

      </div>
      :
      <div>

      </div>
      }


    </div>
  );
}


export default SearchingPage



