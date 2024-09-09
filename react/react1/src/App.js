import './css/App.css';  // css폴더에 App.css가 있고, 연결할 때 사용
import Button from "./Button";
import {useState} from 'react';

function App() {
  var arr1 = [1,2,3];
  // 전개연산자 : 배열이나 객체를 복사할 때 사용, 주소를 공유하지 않고 값만 같게 복사
  var arr2 = [...arr1]; 
  arr2.push(4);
  console.log(arr2);
  console.log(arr1);

  /*
  appleCount 변수에 arr3에 있는 1이 저장되고
  bananaCount 변수에 arr3에 있는 2가 저장되고
  orangeCount 변수에 arr3에 있는 3이 저장되도록 작성
    => 변수 선언 시 []에 각 변수명을 써주면 해당 번지에 맞는 값들이 변수에 맵핑됨
  */
  var arr3 = [1,2,3];
  var [appleCount, bananaCount, orangeCount] = arr3;
  console.log(appleCount);
  console.log(bananaCount);
  console.log(orangeCount);
  
  var [text1, text2] = ['버튼ON', '버튼OFF'];
  //var on = true;
  var [on, setOn] = useState(true);
  function btnOnClick(){
    alert('ON 버튼 클릭');
    //on = false;
    setOn(false);
  }
  function btnOffClick(){
    alert('OFF 버튼 클릭');
    //on = true;
    setOn(true);
  }
  var [input, setInput] = useState("");
  function change(e){
    var value = e.target.value;
    setInput(value);
  }
  return (
    <div>
      {on ? <Button text={text1} type={"button"} className={"btn"} click={btnOnClick} /> : ''}
      {!on ? <Button text={text2} type={"submit"} className={"btn"} click={btnOffClick} /> : ''}
      {/* <Button type="submit" className={{a:"a"}}/> */}
      <Button />
      <br/>
      <input type={"text"} onChange={change}/>
      <h1>{input}</h1>
    </div>
  );
}

export default App;