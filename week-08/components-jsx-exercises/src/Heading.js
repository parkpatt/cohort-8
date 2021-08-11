const defaultMessage = "Meerkat";

function Heading({ message = defaultMessage }) {
  return <h2>{message}</h2>
}

export default Heading;