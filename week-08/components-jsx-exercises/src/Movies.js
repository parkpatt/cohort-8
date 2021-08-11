import Heading from "./Heading";
import Movie from "./Movie";

function Movies() {
  const movies = [
    { id: 1, title: 'Toy Story', releaseYear: 1995 },
    { id: 2, title: 'The Iron Giant', releaseYear: 1999 },
    { id: 3, title: 'Spider-Man: Into the Spider-Verse', releaseYear: 2018 },
  ];

  return <>
    <Heading message="Movies" />
    {movies.map(movie => <Movie key={movie.id} movie={movie} />)}
  </>
}

export default Movies;