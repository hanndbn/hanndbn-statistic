import React from 'react';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from '../header-components';

export const EntitiesMenu = props => (
  // tslint:disable-next-line:jsx-self-close
  <NavDropdown icon="th-list" name="Entities" id="entity-menu">
    <DropdownItem tag={Link} to="/entity/category">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;Category
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/category-statistic">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;Category Statistic
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/item">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;Item
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/item-statistic">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;Item Statistic
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/shop">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;Shop
    </DropdownItem>
    <DropdownItem tag={Link} to="/entity/shop-statistic">
      <FontAwesomeIcon icon="asterisk" fixedWidth />
      &nbsp;Shop Statistic
    </DropdownItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
