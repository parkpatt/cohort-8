
function Movie({ movie }) {
  return <div>
    <span>{movie.title}</span>
    &nbsp; 
    <span>{movie.releaseYear}</span>
  </div>
}

export default Movie;