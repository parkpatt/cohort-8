import { render, screen } from '@testing-library/react';
import userEvent, { useEvent } from '@testing-library/user-event';
import Textbox from './Textbox';

it('should start out empty', () => {
  render(<Textbox />);
  const textbox = document.querySelector('input[type="text"]');
  expect(textbox.value).toEqual('');
});

it('should start out with value', () => {
  render(<Textbox value="test" />);
  const textbox = document.querySelector('input[type="text"]');
  expect(textbox.value).toEqual('test');
});

it('should chnage span text', () => {
  render(<Textbox value="test" />);
  const textbox = document.querySelector('input[type="text"]');
  userEvent.type(document.querySelector('input[type="text"]'), "New value");

  const spantext = screen.getByText(/New value/i);
  expect(spantext).toBeInTheDocument();
});