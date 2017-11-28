import {MainPage} from './main.po';
import {browser} from "protractor";

describe('fga-frontend Main', () => {
  let page: MainPage;

  beforeEach(() => {
    page = new MainPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitle()).toBe('We recommend');
  });

});
