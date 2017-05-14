var demoApp = angular.module('demoApp.services', []);

demoApp.service('countryService', function($http) {
	
	this.url = 'api/countryList';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
	
	
	this.save = function(country) {
		if (country.id) {
			return $http.put(this.url + '/' + country.id, country);
		} else {
			return $http.post(this.url, country);
		}
	};
});
demoApp.service('cityService', function($http) {
	
	this.url = 'api/cityList';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
	 
	
	this.save = function(city) {
		if (city.id) {
			return $http.put(this.url + '/' + city.id, city);
		} else {
			return $http.post(this.url, city);
		}
	};
});
demoApp.service('sportShopBrandService', function($http) {
	
	this.url = 'api/sportShopBrandList';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
	
	
	this.save = function(sportShopBrand) {
		if (sportShopBrand.id) {
			return $http.put(this.url + '/' + sportShopBrand.id, sportShopBrand);
		} else {
			return $http.post(this.url, sportShopBrand);
		}
	};
});
demoApp.service('sportShopService', function($http) {
	
	this.url = 'api/sportShopList';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
 
	this.save = function(sportShop) {
		if (sportShop.id) {
			return $http.put(this.url + '/' + sportShop.id, sportShop);
		} else {
			return $http.post(this.url, sportShop);
		}
	};
});
demoApp.service('sellerService', function($http) {
	
	this.url = 'api/sellerList';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
	
	
	this.save = function(seller) {
		if (seller.id) {
			return $http.put(this.url + '/' + seller.id, seller);
		} else {
			return $http.post(this.url, seller);
		}
	};
});
demoApp.service('adminService', function($http) {
	
	this.url = 'api/adminList';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
	
	
	this.save = function(admin) {
		if (admin.id) {
			return $http.put(this.url + '/' + admin.id, admin);
		} else {
			return $http.post(this.url, admin);
		}
	};
});
demoApp.service('menuItemCategoryService', function($http) {
	
	this.url = 'api/menuItemCategoryList';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
	
	
	this.save = function(menuItemCategory) {
		if (menuItemCategory.id) {
			return $http.put(this.url + '/' + menuItemCategory.id, menuItemCategory);
		} else {
			return $http.post(this.url, menuItemCategory);
		}
	};
});
demoApp.service('menuItemService', function($http) {
	
	this.url = 'api/menuItemList';
	
	this.getOne = function(id) {
		return $http.get(this.url + '/' + id);
	};
	
	this.remove = function(id) {
		return $http.delete(this.url + '/' + id);
	};
	
	this.getAll = function(name, page) {
		return $http.get(this.url, { params: {'name': name, 'page': page}});
	};
	 
	this.save = function(menuItem) {
		if (menuItem.id) {
			return $http.put(this.url + '/' + menuItem.id, menuItem);
		} else {
			return $http.post(this.url, menuItem);
		}
	};
});
