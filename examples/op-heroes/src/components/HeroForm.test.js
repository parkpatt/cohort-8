import { act, render } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import { MemoryRouter } from 'react-router';
import { Route } from 'react-router-dom';
import * as api from '../api/heroApi';
import HeroForm from './HeroForm';

it('should add a new hero', async () => {
  api.saveHero = jest.fn().mockImplementation(() => Promise.resolve({}));

  await act(async() => {
    render(
      <MemoryRouter>
        <HeroForm />
      </MemoryRouter>
    );
  });

  userEvent.type(document.getElementById("alias"), "Bunny");
  userEvent.type(document.getElementById("fullName"), "Bobby McGee");
  userEvent.type(document.getElementById("faction"), "Bowlers");
  userEvent.click(document.querySelector('button[type="submit"]'));

  expect(api.saveHero).toBeCalledTimes(1);

  expect(api.saveHero.mock.calls[0][0]).toStrictEqual({ 
    id: 0, 
    alias: "Bunny", 
    fullName: "Bobby McGee", 
    faction: "Bowlers", 
    abilities: []
  });
});

it('should update a hero', async () => {

  const hero = { 
    id: 1, 
    alias: "Bunny", 
    fullName: "Bobby McGee", 
    faction: "Bowlers", 
    abilities: []
  }

  api.fetchHero = jest.fn().mockImplementation(() => Promise.resolve(hero));
  api.saveHero = jest.fn().mockImplementation(() => Promise.resolve({}));

  await act(async() => {
    render(
      <MemoryRouter initialEntries={["/edit/1"]}>
        <Route path={["/edit/:heroId"]}>
          <HeroForm />
        </Route>
      </MemoryRouter>
    );
  });

  userEvent.clear(document.getElementById("alias"));
  userEvent.type(document.getElementById("alias"), "Buddy");
  userEvent.clear(document.getElementById("fullName"));
  userEvent.type(document.getElementById("fullName"), "Barbara McGee");
  userEvent.clear(document.getElementById("faction"));
  userEvent.type(document.getElementById("faction"), "Bowlerz");
  userEvent.click(document.querySelector('button[type="submit"]'));

  expect(api.saveHero).toBeCalledTimes(1);

  expect(api.saveHero.mock.calls[0][0]).toStrictEqual({ 
    id: 1, 
    alias: "Buddy", 
    fullName: "Barbara McGee", 
    faction: "Bowlerz", 
    abilities: []
  });

});
