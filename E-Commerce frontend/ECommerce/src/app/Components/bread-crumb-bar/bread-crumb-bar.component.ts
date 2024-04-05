import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { NavigationEnd, Router} from '@angular/router';

@Component({
  selector: 'bread-crumb-bar',
  templateUrl: './bread-crumb-bar.component.html',
  styleUrls: ['./bread-crumb-bar.component.css']
})
export class BreadCrumbBarComponent implements OnInit {
  path: string = "";

  constructor(private router: Router){
   
  }  

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.path=event.url;
      }
    });
  }
}


// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
// import { filter } from 'rxjs/operators';

// interface Breadcrumb {
//   label: string;
//   url: string;
// }

// @Component({
//   selector: 'app-breadcrumb',
//   templateUrl: './bread-crumb-bar.component.html',
//   styleUrls: ['./bread-crumb-bar.component.css']
// })
// export class  BreadCrumbBarComponent implements OnInit {
//   breadcrumbs: Breadcrumb[] = [];

//   constructor(
//     private router: Router,
//     private activatedRoute: ActivatedRoute
//   ) {}

//   ngOnInit(): void {
//     this.router.events.pipe(
//       filter(event => event instanceof NavigationEnd)
//     ).subscribe(() => {
//       this.breadcrumbs = this.buildBreadcrumb(this.activatedRoute.root);
//     });
//   }

//   buildBreadcrumb(route: ActivatedRoute, url: string = '', breadcrumbs: Breadcrumb[] = []): Breadcrumb[] {
//     // Ottieni il percorso della rotta
//     const path = route.routeConfig ? route.routeConfig.path : '';

//     // Costruisci l'URL completo fino a questo punto del percorso
//     const nextUrl = path ? ${url}/${path} : url;

//     // Aggiungi un breadcrumb se la rotta ha un titolo
//     if (route.routeConfig && route.routeConfig.data && route.routeConfig.data['breadcrumb']) {
//       const breadcrumb: Breadcrumb = {
//         label: route.routeConfig.data['breadcrumb'],
//         url: nextUrl,
//       };
//       breadcrumbs.push(breadcrumb);
//     }