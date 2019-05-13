import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DepartmentListComponent } from './department-list/department-list.component';
import { EmployeesListComponent } from './employees-list/employees-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { DepartmentDetailComponent } from './department-detail/department-detail.component';
import { DepartmentOverviewComponent } from './department-overview/department-overview.component';
import { DepartmentContactComponent } from './department-contact/department-contact.component';

const routes: Routes = [
  { path: '', redirectTo: '/departments', pathMatch : 'full'},
  { path:'departments', component: DepartmentListComponent },
  { path:'employees', component: EmployeesListComponent },
  { path: 'departments/:id', component: DepartmentDetailComponent,
    children: [
      { path: 'overview', component: DepartmentOverviewComponent },
      { path: 'contact', component: DepartmentContactComponent }
    ]},
  { path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
export const routingComponents = [DepartmentListComponent, 
                                  EmployeesListComponent, 
                                  PageNotFoundComponent,
                                  DepartmentDetailComponent,
                                  DepartmentOverviewComponent]