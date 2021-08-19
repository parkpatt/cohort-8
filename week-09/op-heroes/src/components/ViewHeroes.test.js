import { act, render } from '@testing-library/react';
import { MemoryRouter } from 'react-router';
import * as api from '../api/heroApi';
import ViewHeroes from './ViewHeroes';

const heroes = [
  {
    id: 1, alias: "Artemis", fullName: "Kyana Osai", faction: "Survivalists", abilities: [
      { id: 1, name: "Double Shot" },
      { id: 2, name: "Place Snare" }
    ]
  },
  {
    id: 2, alias: "Little Jenny", fullName: "Jenny Tsang", faction: "Red Ragons", abilities: [
      { id: 5, name: "Taunt" }
    ]
  },
  {
    id: 3, alias: "Jane", fullName: "Jane", faction: "Animals", abilities: [
      { id: 3, name: "Act Cute" },
      { id: 4, name: "9 Lives" }
    ]
  }
];

it('should display 3 heroes', async () => {
  api.fetchAll = jest.fn().mockImplementation(() => Promise.resolve(heroes));

  await act(async () => {
    render(
      <MemoryRouter>
        <ViewHeroes />
      </MemoryRouter>
    );
  });

  expect(api.fetchAll).toBeCalledTimes(1);
  const cards = document.querySelectorAll(".card");
  expect(cards).toHaveLength(3);
})