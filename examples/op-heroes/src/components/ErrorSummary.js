
function ErrorSummary({ errors }) {
  return <>
    {errors.length > 0 ? <div className="alert alert-danger">
      {errors.map(err => <div>{err}</div>)}
    </div> : null }
  </>
}

export default ErrorSummary;