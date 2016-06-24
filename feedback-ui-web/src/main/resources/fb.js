(function (angular, html2canvas) {
    'use strict';

    angular.module('feedback.ui', [])
        .directive('feedbackUi', function () {
            return {
                controller: function ($scope, $http, $window) {
                    var ctrl = this;
                    ctrl.feedback = {};
                    ctrl.showForm = false;

                    ctrl.submit = function () {
                        ctrl.inProgress = true;
                        html2canvas(document.body, {
                            onrendered: function (canvas) {
                                var screenshot = canvas.toDataURL("image/png")
                                    .substring('data:image/png;base64,'.length);
                                $scope.$apply(function () {
                                    if (ctrl.feedback.message) {
                                        $http.post('__feedback/message', {
                                            from: ctrl.feedback.from,
                                            url: $window.location.href,
                                            userAgent: $window.navigator.userAgent,
                                            message: ctrl.feedback.message,
                                            screenshot: screenshot
                                        }).then(function () {
                                            ctrl.feedback = {};
                                            ctrl.showForm = false;
                                        }).finally(function () {
                                            ctrl.inProgress = false;
                                        });
                                    }
                                });
                            }
                        });
                    };
                },
                controllerAs: 'ctrl',
                link: function (scope, elem) {
                    elem.appendTo(angular.element('body'));
                },
                template: '<div data-html2canvas-ignore class="visible-lg visible-md panel panel-primary" style="z-index: 999999; position: fixed;width: 300px;bottom: 1px;right: 1px; margin: 0;">' +
                '<div class="panel-heading" style="cursor: pointer;" ng-click="ctrl.showForm=!ctrl.showForm">' +
                '<i class="fa fa-fw fa-exchange" ng-hide="ctrl.inProgress"></i> ' +
                '<i class="fa fa-fw fa-spin fa-spinner" ng-show="ctrl.inProgress"></i> ' +
                '<i class="fa fw-fw fa-exchange" ng-show="ctrl.success"></i> ' +
                'Feedback</div>' +
                '<div class="panel-body" ng-show="ctrl.showForm">' +
                '<div ng-show="ctrl.inProgress" style="position:absolute;top:0;left:0;right:0;bottom:0;background: #eee;opacity: 0.5;text-align: center;padding-top: 30px;"></div>' +
                '<form name="feedbackForm" ng-submit="feedbackForm.$valid && ctrl.submit()">' +
                '<div class="form-group"><input type="text" class="form-control" ng-model="ctrl.feedback.from" placeholder="Name" required /></div>' +
                '<div class="form-group">' +
                '<textarea class="form-control" name="message" required ng-model="ctrl.feedback.message" placeholder="Nachricht"></textarea>' +
                '</div>' +
                '<div>' +
                '<button type="submit" class="btn btn-primary" ng-disabled="feedbackForm.$invalid">Absenden</button>' +
                '</div>' +
                '</form>' +
                '</div>' +
                '</div>'
            };
        });
}(window.angular, window.html2canvas));