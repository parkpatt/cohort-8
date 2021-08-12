import { useState } from 'react';
import ChildElement from './ChildElement';

function ParentElement() {

  const [checked, setChecked] = useState(false);

  return <>
    <ChildElement checked={checked} onChange={setChecked} />
    <ChildElement checked={checked} onChange={setChecked} />
    <ChildElement checked={checked} onChange={setChecked} />
  </>

}

export default ParentElement;