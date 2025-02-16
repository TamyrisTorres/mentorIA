import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultPageLogoComponent } from './default-page-logo.component';

describe('DefaultPageLogoComponent', () => {
  let component: DefaultPageLogoComponent;
  let fixture: ComponentFixture<DefaultPageLogoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DefaultPageLogoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DefaultPageLogoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
