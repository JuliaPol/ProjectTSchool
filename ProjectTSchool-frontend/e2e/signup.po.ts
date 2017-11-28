import { browser, by, element } from 'protractor';

export class SignupPage {
  navigateTo() {
    return browser.get('/#/signUp');
  }

  getLoginInput() {
    return element(by.id('login_username'));
  }

  getEmailInput() {
    return element(by.id('login_email'));
  }

  getPasswordInput() {
    return element(by.id('login_password'));
  }

  getErrorMessage() {
    return element(by.css('.error.active')).getText();
  }
  getLoginButton() {
    return element(by.buttonText('NEXT'));
  }
  getTitle() {
    return element(by.tagName('h3')).getText();
  }
}
