import './App.css';
import React,{useState, useEffect} from 'react';


const App = () => {
  const [count, setCount] = useState(5);

  const firstPromise = () => {
    return new Promise((res, rej) => {
      setTimeout(() => {
        // res(console.log("first promise"))
        res(1)
      }, 100)
    })
  }

  const secondPromise = () => {
    return new Promise((res, rej) => {
      setTimeout(() => {
        // res(console.log("second promise"))
        res(4)
      }, 10000)
    })

  }

  const click = () => {
    Promise.all([secondPromise(), firstPromise()]).then((values) => {
      console.log(values)
    })
    // console.log(secondPromise())
  }


  const show = () => {
    console.log(count);

  }

  return(
    <div>
      <button onClick={click}>click</button>
      <button onClick={show}>show</button>

    </div>

  )
} 

export default App;
















