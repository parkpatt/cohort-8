
function ConfirmDelete({ hero, confirm, cancel }) {

  return <div className="container">
    <div className="row align-items-center">
      <h2 className="col">{`Delete ${hero.alias}?`}</h2>
    </div>
    <div className="alert alert-danger">Are you sure? {`${hero.alias}`} will be permanently deleted.</div>
    <div className="mb-3">
      <button type="button" className="btn btn-danger mx-3" onClick={confirm}>Delete</button>
      <button type="button" className="btn btn-secondary mx-3" onClick={cancel}>Cancel</button>
    </div>
  </div>
}

export default ConfirmDelete;