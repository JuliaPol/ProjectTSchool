import {SignupPage} from './signup.po';
import {browser} from "protractor";

describe('fga-frontend SignUp', () => {
  let page: SignupPage;

  beforeEach(() => {
    page = new SignupPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getTitle()).toBe('Sign up to');
  });

  it('should show error password', () => {
    page.getLoginInput().sendKeys('jpi287');
    page.getEmailInput().sendKeys('jpi287@mail.ru');
    page.getPasswordInput().sendKeys('1');
    expect(page.getErrorMessage()).toBe('Password must be at least 6 characters long.');
  });

  it('should show error login', () => {
    page.getLoginInput().clear();
    page.getPasswordInput().clear();
    page.getLoginInput().sendKeys('jp');
    page.getPasswordInput().sendKeys('123456');
    expect(page.getErrorMessage()).toBe('Username must be at least 4 characters long.');
  });

  it('should show error email', () => {
    page.getLoginInput().clear();
    page.getEmailInput().clear();
    page.getLoginInput().sendKeys('jpi287');
    page.getEmailInput().sendKeys('jpi287');
    expect(page.getErrorMessage()).toBe('Must be a valid email!');
  });

  it('should signup successful', () => {
    page.getLoginInput().clear();
    page.getEmailInput().clear();
    page.getPasswordInput().clear();
    page.getLoginInput().sendKeys('jpy321');
    page.getEmailInput().sendKeys('jpi287@mail.ru');
    page.getPasswordInput().sendKeys('123456');
    page.getLoginButton().submit();
  });
});
