import { useState } from 'react';

const defaultMin = 1;
const defaultMax = 10;

function Numbers({ min = defaultMin, max = defaultMax }) {

  const nums = [];

  for (let i = min; i <= max; i++) {
    nums.push(i);
  }

  return <ul>
    {nums.map(num => <li key={num}>{num}</li>)}
  </ul>
}

export default Numbers;