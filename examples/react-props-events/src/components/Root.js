import { useState } from 'react';
import Branch from './Branch';

function Root() {

  const [treeType, setTreeType] = useState("pine");

  return (
    <div className="node-container">
      <div>
        <div className="node">
          <h1>Root</h1>
          {treeType}
        </div>
      </div>
      <div>
        <Branch treeType={treeType} handleChange={setTreeType} />
        <Branch treeType={treeType} handleChange={setTreeType} />
      </div>
    </div>
  );
}

export default Root;