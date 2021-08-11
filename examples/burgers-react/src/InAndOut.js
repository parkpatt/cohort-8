import { useState } from 'react';

const InOut = {
    IN: "In",
    OUT: "Out"
};

function InAndOut() {

    const [inOut, setInOut] = useState(InOut.OUT);

    const inOutChange = () => {
        const newVal = inOut == InOut.IN
            ? InOut.OUT
            : InOut.IN;

        setInOut(newVal);
    }

    return (
        <div>
            <label>{inOut}
                <input type="checkbox" onChange={inOutChange}></input>
            </label>
        </div>
    )
}

export default InAndOut;