import Leaf from "./Leaf";

function Branch({ treeType, handleChange }) {
  return (
    <div className="node-container">
      <div>
        <div className="node">
          <h1>Branch</h1>
          {treeType}
        </div>
      </div>
      <div>
        <Leaf treeType={treeType} handleChange={handleChange} />
        <Leaf treeType={treeType} handleChange={handleChange} />
      </div>
    </div>
  );
}

export default Branch;