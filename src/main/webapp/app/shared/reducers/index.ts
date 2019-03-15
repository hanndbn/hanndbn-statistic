import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import category, {
  CategoryState
} from 'app/entities/category/category.reducer';
// prettier-ignore
import categoryStatistic, {
  CategoryStatisticState
} from 'app/entities/category-statistic/category-statistic.reducer';
// prettier-ignore
import item, {
  ItemState
} from 'app/entities/item/item.reducer';
// prettier-ignore
import itemStatistic, {
  ItemStatisticState
} from 'app/entities/item-statistic/item-statistic.reducer';
// prettier-ignore
import shop, {
  ShopState
} from 'app/entities/shop/shop.reducer';
// prettier-ignore
import shopStatistic, {
  ShopStatisticState
} from 'app/entities/shop-statistic/shop-statistic.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly category: CategoryState;
  readonly categoryStatistic: CategoryStatisticState;
  readonly item: ItemState;
  readonly itemStatistic: ItemStatisticState;
  readonly shop: ShopState;
  readonly shopStatistic: ShopStatisticState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  category,
  categoryStatistic,
  item,
  itemStatistic,
  shop,
  shopStatistic,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar
});

export default rootReducer;
