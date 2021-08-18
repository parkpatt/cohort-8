import { useState } from 'react';

function Textbox({ value }) {

  const [text, setText] = useState(value);

  const onChange = (evt) => {
    setText(evt.target.value);
  } 

  return <>
    <input type="text" value={text} onChange={onChange}></input>
    <span>{text}</span>
  </>
}

export default Textbox;