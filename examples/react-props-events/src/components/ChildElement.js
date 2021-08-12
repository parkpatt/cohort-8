
function ChildElement({ checked, onChange }) {
  return <label>Checked
      <input type="checkbox" checked={checked} onChange={() => onChange(!checked)} />
    </label>
}

export default ChildElement;