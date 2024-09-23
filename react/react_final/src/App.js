import './App.css';
import { Button1, Button2 } from "./Button";
import { useState } from 'react';

function App() {
  const text1 = "1";
  const text2 = "2";
  const click = (text)=>{alert(text)};
  let [str, setStr] = useState("");

  return (
    <div>
      <Button1 text={text1} className={"btn"} click={()=>{click(text1)}}/>
      <Button2 text={text2} className={"btn"} click={()=>{click(text2)}}/>
      <br/>
      <input onChange={(e)=>setStr(e.target.value)}/>
      <Button1 text={"버튼"} className={"btn"} click={()=>click(str)}/>
    </div>
  );
}

export default App;