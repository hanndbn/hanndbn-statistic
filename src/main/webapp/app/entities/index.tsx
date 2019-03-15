import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Category from './category';
import CategoryStatistic from './category-statistic';
import Item from './item';
import ItemStatistic from './item-statistic';
import Shop from './shop';
import ShopStatistic from './shop-statistic';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/category`} component={Category} />
      <ErrorBoundaryRoute path={`${match.url}/category-statistic`} component={CategoryStatistic} />
      <ErrorBoundaryRoute path={`${match.url}/item`} component={Item} />
      <ErrorBoundaryRoute path={`${match.url}/item-statistic`} component={ItemStatistic} />
      <ErrorBoundaryRoute path={`${match.url}/shop`} component={Shop} />
      <ErrorBoundaryRoute path={`${match.url}/shop-statistic`} component={ShopStatistic} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
