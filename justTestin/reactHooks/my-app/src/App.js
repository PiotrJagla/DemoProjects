import './App.css';
import React,{useState, useEffect} from 'react';


const App = () => {
  const [resourceType, setResourceType] = useState('posts')

  // useEffect(() => {
  //   console.log('render')

  // }, [resourceType])
  useEffect(() => {
    console.log('render')

  })

  return (
    <div className="App">
      <button onClick={() => setResourceType('posts')}>Posts</button>
      <button onClick={() => setResourceType('users')}>Users</button>
      <button onClick={() => setResourceType('comments')}>Comments</button>
      <h1>{resourceType}</h1>
    </div>
  );
}

export default App;
