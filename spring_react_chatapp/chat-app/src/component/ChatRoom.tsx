import React, {useState} from 'react';
import SockJS from 'sockjs-client';
import {over} from 'stompjs';

interface message {
    username: string,
    recievername: string,
    connected: boolean,
    message: string
}
var stompClient:any =null;
const ChatRoom = () => {

    const [userData, setUserData] = useState<message>({
        username: "",
        recievername: "",
        connected: false,
        message:""

    });
    const [publicChats, setPublicChats] = useState<any>([]);
    const [privateChats, setPrivateChats] = useState<any>(new Map());
    const [tab, setTab] = useState<string>("CHATROOM");





    const handleValue= (event: any) => {
        const {value, name}=event.target;
        setUserData({...userData, [name]: value});
    }
    const registerUser = () => {

        let Sock=new SockJS('http://localhost:8081/ws');

        stompClient = over(Sock);
        stompClient.connect({}, onConnected, onError);
    }

    const onConnected =() => {
        setUserData({...userData, "connected":true});
        stompClient.subscribe('/chatroom/public', onPublicMessageReceived);
        stompClient.subscribe('/user/' + userData.username + '/private', onPrivateMessageReceived);
        userJoin();
    }
    const userJoin = () => {
        if(stompClient) {
            let chatMessage= {
                senderName:userData.username,
                status:'JOIN'
            };
            stompClient.send('/app/message', {}, JSON.stringify(chatMessage));
        }
    }
    const onPublicMessageReceived = (payload: any) => {
        let payloadData = JSON.parse(payload.body);
        switch(payloadData.status){

            case "JOIN":
                if(!privateChats.get(payloadData.senderName)) {

                    privateChats.set(payloadData.senderName, []);
                    setPrivateChats(new Map(privateChats));
                }
                break;
            case "MESSAGE":
                publicChats.push(payloadData);
                setPublicChats([...publicChats]);
                break;
        }
    }

    const onPrivateMessageReceived = (payload: any) => {
        let payloadData=JSON.parse(payload.body);
        if(privateChats.get(payloadData.senderName)){
            privateChats.get(payloadData.senderName).push(payloadData);
            setPrivateChats(new Map(privateChats));
        }
        else{

            let list=[];
            list.push(payloadData);
            privateChats.set(payloadData.senderName, list);
            setPrivateChats(new Map(privateChats));
        }

    }

    const onError = (err: any) => {

        console.log(err);
    }


    const sendPublicMessage = () => {
        if(stompClient) {
            let chatMessage= {
                senderName:userData.username,
                message:userData.message,
                status:'MESSAGE'
            };
            stompClient.send('/app/message', {}, JSON.stringify(chatMessage));
            setUserData({...userData,"message": ""});
        }
    }

    const sendPrivateMessage= () => {
        if(stompClient) {
            let chatMessage= {
                senderName:userData.username,
                receiverName:tab,
                message:userData.message,
                status:'MESSAGE'
            };
            if(userData.username !== tab){
                privateChats.get(tab).push(chatMessage);
                setPrivateChats(new Map(privateChats));
            }
            stompClient.send('/app/private-message', {}, JSON.stringify(chatMessage));
            setUserData({...userData,"message": ""});
        }
    }

  return (
    <div className = "container">
        {userData.connected?
            <div className="chat-box">
                <div className="member-list">
                    <ul>
                        <li onClick={() => {setTab("CHATROOM")}} className={`member ${tab==="CHATROOM" && "active"}`}>Chatroom</li>
                        {[...privateChats.keys()].map((name,index) =>(
                            <li onClick ={() => {setTab(name)}} key={index} className={`member ${tab===name && "active"}`} >
                                {name}
                            </li>
                        ))}
                    </ul>

                </div>
                {tab ==="CHATROOM" &&<div className="chat-content">
                    <ul className="chat-messages">

                        {publicChats.map((chat:any,index:any) =>(
                            <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                                {chat.senderName !== userData.username && <div className='avatar'>{chat.senderName}</div>}
                                <div className='message-data'>{chat.message}</div>
                                {chat.senderName === userData.username && <div className='avatar self' >{chat.senderName}</div>}
                            </li>
                        ))}
                    </ul>
                    <div className="send-message">
                        <input type='text' className='input-message' placeholder='enter public message' name='message' value={userData.message} onChange={handleValue}/>
                        <button type='button' className='send-button' onClick={sendPublicMessage}>send</button>

                    </div>
                </div>}
                {tab !=="CHATROOM" &&<div className='chat-content'>
                    <ul className='chat-message'>
                        {[...privateChats.get(tab)].map((chat,index) =>(
                            <li className={`message ${chat.senderName === userData.username && "self"}`} key={index}>
                                {chat.senderName !== userData.username && <div className='avatar'>{chat.senderName}</div>}
                                <div className='message-data'>{chat.message}</div>
                                {chat.senderName === userData.username && <div className='avatar self' >{chat.senderName}</div>}
                            </li>
                        ))}
                    </ul>
                    <div className="send-message">
                        <input type='text' name='message' className='input-message' placeholder={`enter private message for ${tab}`} value={userData.message} onChange={handleValue}/>
                        <button type='button' className='send-button' onClick={sendPrivateMessage}>send</button>

                    </div>
                </div>}


            </div>
        
        :
            <div className = 'register'>

                <input id='user-name' placeholder='Enter the user name' value={userData.username} name='username' onChange={handleValue}/>
                <button type='button' onClick={registerUser} > connect</button>
            </div>
        }

    </div>
  )
}

export default ChatRoom