import {AppPage} from './app.po';
import {browser} from "protractor";

describe('fga-frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitle()).toBe('Welcome to');
  });

  it('should show error', () => {
    page.getLoginButton().click();
    expect(page.getErrorMessage()).toBe('Invalid user name or password');
  });

  it('should login successful', () => {
    page.getEmailInput().sendKeys('ivan_88');
    page.getPasswordInput().sendKeys('4321');
    page.getLoginButton().submit();
    page.navigateToEmployee();
  });

  it('should login successful customer', () => {
    page.getEmailInput().clear();
    page.getPasswordInput().clear();
    page.getEmailInput().sendKeys('jpi287');
    page.getPasswordInput().sendKeys('1234');
    page.getLoginButton().submit();
  });
});
