var socialMediaApp = angular.module('socialMediaApp', []);

socialMediaApp.controller('ContentListCtrl', function ($scope, $http) {
    $http.get('home/contents').success(function (data) {
        $scope.contents = data;
    });
    /*$scope.contents = [
     {'id': '111',
     'title': 'Test',
     'timestamp': new Date(),
     'authorId': '2222',
     'author': 'Marco Bazzoni',
     'post': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero.'},
     {'id': '111',
     'title': 'Test',
     'timestamp': new Date(),
     'authorId': '2222',
     'author': 'Marco Bazzoni',
     'post': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero.'},
     {'id': '111',
     'title': 'Test',
     'timestamp': new Date(),
     'authorId': '2222',
     'author': 'Marco Bazzoni',
     'post': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero. Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus laudantium odit aliquam repellat tempore quos aspernatur vero.'}
     ];*/
}).controller('MessageListCtrl', function ($scope, $http) {
    $scope.messages = [
        {
            'id': '111',
            'subject': 'Test',
            'author': 'Marco Bazzoni',
            'text': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus.'
        },
        {
            'id': '111',
            'subject': 'Test',
            'author': 'Donald Duck',
            'text': 'Fast just got faster with Nexus S.'
        }
    ];
}).controller('LogListCtrl', function ($scope, $http) {
    $scope.logs = [
        {
            'activity': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus.',
            'timestamp': new Date()
        },
        {
            'activity': 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore, perspiciatis adipisci accusamus.',
            'timestamp': new Date()
        }
    ];
});