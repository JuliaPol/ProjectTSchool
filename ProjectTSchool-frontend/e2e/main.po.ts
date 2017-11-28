import { browser, by, element } from 'protractor';

export class MainPage {
  navigateTo() {
    return browser.get('/#/main-page');
  }

  getEmailInput() {
    return element(by.css('.username'));
  }

  getPasswordInput() {
    return element(by.css('.password'));
  }

  getErrorMessage() {
    return element(by.css('.error.active')).getText();
  }
  getLoginButton() {
    return element(by.buttonText('NEXT'));
  }
  getLogoutButton() {
    return element(by.id('logout'));
  }
  getTitle() {
    return element(by.id('weRecommend')).getText();
  }
}
