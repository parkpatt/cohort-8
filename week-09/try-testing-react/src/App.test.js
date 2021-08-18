import { render, screen } from '@testing-library/react';
import App from './App';

test('renders learn react link', () => {
  render(<App />);
  const h2 = screen.getByText(/sub header/i);
  expect(h2).toBeInTheDocument();
});
